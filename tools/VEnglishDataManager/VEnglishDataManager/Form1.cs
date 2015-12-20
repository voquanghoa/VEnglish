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
	public partial class Form1 : Form
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
				var realData = LoadDir(TxtDataPath.Text);
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

		private DataItem LoadDir(string path)
		{
			var dataItem = new DataItem();

			dataItem.FileName = Path.GetFileName(path);
			dataItem.Display = DefaultFormat(dataItem.FileName);

			if (File.GetAttributes(path).HasFlag(FileAttributes.Directory))
			{
				List<string> files = Directory.EnumerateFileSystemEntries(path).ToList();

				dataItem.Children = files.Select(x => LoadDir(Path.Combine(path, x))).ToList();
			}

			return dataItem;
		}

		private string DefaultFormat(string fileName)
		{
			var withoutExt = Path.GetFileNameWithoutExtension(fileName);
			var withSpace = withoutExt.Replace('_', ' ');
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
	}
}
