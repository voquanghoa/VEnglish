using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using Newtonsoft.Json;

namespace VEnglishDataManager
{
	public partial class Form1 : Form, IComparer<String>
	{
		private const string DefaultDataFile = "data.json";
		
		public Form1()
		{
			InitializeComponent();
		}

		private void BtnOpenJsonFile_Click(object sender, EventArgs e)
		{
			if (DialogOpenFile.ShowDialog() != DialogResult.Cancel)
			{
				TxtJsonPath.Text = DialogOpenFile.FileName;
			}
		}

		private void BtnSelectDataPath_Click(object sender, EventArgs e)
		{
			DialogSelectFolder.SelectedPath = TxtDataPath.Text;
            if (DialogSelectFolder.ShowDialog() != DialogResult.Cancel)
			{
				TxtDataPath.Text = DialogSelectFolder.SelectedPath;
				if (TxtJsonPath.TextLength == 0)
				{
					TxtJsonPath.Text = Path.Combine(DialogSelectFolder.SelectedPath, DefaultDataFile);
                }
			}
		}

		private void BtnLoadData_Click(object sender, EventArgs e)
		{
			if (TxtDataPath.TextLength == 0 || TxtJsonPath.TextLength == 0)
			{
				ShowError("Chưa chọn đường dẫn này nọ !!!");
			}
			else
			{
				var realData = LoadDir(TxtDataPath.Text, 0);
				var jsonData = LoadJson(TxtJsonPath.Text);
				MergeDataItem(realData, jsonData);
				SaveJson(TxtJsonPath.Text, realData);
				ShowInfo("Đã lưu ra file !!!");
			}
        }

		private void MergeDataItem(DataItem realData, DataItem jsonData)
		{
			if (realData != null && jsonData != null)
			{
				if(!string.IsNullOrEmpty(jsonData.Display))
				{
					realData.Display = jsonData.Display;
				}

				if (realData.Children != null && jsonData.Children!=null)
				{
					foreach (var child in realData.Children)
					{
						var jsonChild = jsonData.Children.FirstOrDefault(x => string.Equals(x.FileName, child.FileName));
						MergeDataItem(child, jsonChild);
					}
				}
			}
		}

		private DataItem LoadDir(string path, int level)
		{
			var dataItem = new DataItem();

			dataItem.FileName = Path.GetFileName(path);
			dataItem.Display = DefaultFormat(dataItem.FileName, level>0);

			if (File.GetAttributes(path).HasFlag(FileAttributes.Directory))
			{
				var files = Directory.EnumerateFileSystemEntries(path).Where(x => !x.EndsWith(DefaultDataFile)).ToList().OrderBy(x => x, this);

				dataItem.Children = files.Select(x => LoadDir(Path.Combine(path, x), level + 1)).ToList();
			}

			return dataItem;
		}

		private string DefaultFormat(string fileName, bool toLower)
		{
			var withoutExt = Path.GetFileNameWithoutExtension(fileName);
			var withSpace = withoutExt.Replace('_', ' ');
			if (toLower)
			{
				withSpace = withSpace.ToLower();
			}
			var capital = char.ToUpper(withSpace[0]) + withSpace.Substring(1);
			return capital;
		}

		private DataItem LoadJson(string fileName)
		{
			try
			{
				return JsonConvert.DeserializeObject<DataItem>(File.ReadAllText(fileName));
			}
			catch
			{
				return new DataItem();
			}
		}

		private void SaveJson(string fileName, DataItem dataItem)
		{
			try
			{
				File.WriteAllText(fileName, JsonConvert.SerializeObject(dataItem));
			}
			catch (Exception ex)
			{
				ShowError(ex.Message);
            }
		}

		private void ShowError(string error)
		{
			MessageBox.Show(error, "Error", MessageBoxButtons.OK, MessageBoxIcon.Exclamation);
		}

		private void ShowInfo(string error)
		{
			MessageBox.Show(error, "Information", MessageBoxButtons.OK, MessageBoxIcon.Information);
		}

		public int Compare(string x, string y)
		{
			x = x.Trim();
			y = y.Trim();

			if (x.LastIndexOf('.') > 0)
			{
				x = x.Substring(0, x.LastIndexOf('.'));
			}

			if (y.LastIndexOf('.') > 0)
			{
				y = y.Substring(0, y.LastIndexOf('.'));
			}

			int xLastSpace = x.LastIndexOf(' ');
			int yLastSpace = y.LastIndexOf(' ');

			if (xLastSpace < 0)
			{
				xLastSpace = x.LastIndexOf('_');
			}

			if (yLastSpace < 0)
			{
				yLastSpace = y.LastIndexOf('_');
            }

			try
			{
				if (yLastSpace >= 0 && xLastSpace >= 0)
				{
					string strNumX = x.Substring(xLastSpace + 1);
					string strNumY = y.Substring(yLastSpace + 1);

					int numX = int.Parse(strNumX);
					int numY = int.Parse(strNumY);

					return numX - numY;
				}
			}
			catch
			{
				Console.WriteLine(x + " -- " + y);
			}
			return x.CompareTo(y);
		}
	}
}
