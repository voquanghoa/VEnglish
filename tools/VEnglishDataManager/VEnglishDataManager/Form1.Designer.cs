namespace VEnglishDataManager
{
	partial class Form1
	{
		/// <summary>
		/// Required designer variable.
		/// </summary>
		private System.ComponentModel.IContainer components = null;

		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		/// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
		protected override void Dispose(bool disposing)
		{
			if (disposing && (components != null))
			{
				components.Dispose();
			}
			base.Dispose(disposing);
		}

		#region Windows Form Designer generated code

		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		private void InitializeComponent()
		{
			this.label1 = new System.Windows.Forms.Label();
			this.TxtDataPath = new System.Windows.Forms.TextBox();
			this.BtnSelectDataPath = new System.Windows.Forms.Button();
			this.label2 = new System.Windows.Forms.Label();
			this.TxtJsonPath = new System.Windows.Forms.TextBox();
			this.BtnOpenJsonFile = new System.Windows.Forms.Button();
			this.groupBox1 = new System.Windows.Forms.GroupBox();
			this.TvDataShow = new System.Windows.Forms.TreeView();
			this.DialogOpenFile = new System.Windows.Forms.OpenFileDialog();
			this.DialogSelectFolder = new System.Windows.Forms.FolderBrowserDialog();
			this.BtnLoadData = new System.Windows.Forms.Button();
			this.label3 = new System.Windows.Forms.Label();
			this.TxtName = new System.Windows.Forms.TextBox();
			this.label4 = new System.Windows.Forms.Label();
			this.txtDisplay = new System.Windows.Forms.TextBox();
			this.EditPanel = new System.Windows.Forms.Panel();
			this.groupBox1.SuspendLayout();
			this.EditPanel.SuspendLayout();
			this.SuspendLayout();
			// 
			// label1
			// 
			this.label1.AutoSize = true;
			this.label1.Location = new System.Drawing.Point(11, 20);
			this.label1.Name = "label1";
			this.label1.Size = new System.Drawing.Size(59, 13);
			this.label1.TabIndex = 0;
			this.label1.Text = "Root folder";
			// 
			// TxtDataPath
			// 
			this.TxtDataPath.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
			this.TxtDataPath.Location = new System.Drawing.Point(76, 17);
			this.TxtDataPath.Name = "TxtDataPath";
			this.TxtDataPath.Size = new System.Drawing.Size(415, 20);
			this.TxtDataPath.TabIndex = 1;
			this.TxtDataPath.Text = "D:\\Project\\VEnglish\\app\\src\\main\\assets";
			// 
			// BtnSelectDataPath
			// 
			this.BtnSelectDataPath.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
			this.BtnSelectDataPath.Location = new System.Drawing.Point(503, 14);
			this.BtnSelectDataPath.Name = "BtnSelectDataPath";
			this.BtnSelectDataPath.Size = new System.Drawing.Size(75, 23);
			this.BtnSelectDataPath.TabIndex = 2;
			this.BtnSelectDataPath.Text = "Open";
			this.BtnSelectDataPath.UseVisualStyleBackColor = true;
			this.BtnSelectDataPath.Click += new System.EventHandler(this.BtnSelectDataPath_Click);
			// 
			// label2
			// 
			this.label2.AutoSize = true;
			this.label2.Location = new System.Drawing.Point(12, 52);
			this.label2.Name = "label2";
			this.label2.Size = new System.Drawing.Size(29, 13);
			this.label2.TabIndex = 0;
			this.label2.Text = "Json";
			// 
			// TxtJsonPath
			// 
			this.TxtJsonPath.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
			this.TxtJsonPath.Location = new System.Drawing.Point(76, 49);
			this.TxtJsonPath.Name = "TxtJsonPath";
			this.TxtJsonPath.Size = new System.Drawing.Size(415, 20);
			this.TxtJsonPath.TabIndex = 1;
			// 
			// BtnOpenJsonFile
			// 
			this.BtnOpenJsonFile.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
			this.BtnOpenJsonFile.Location = new System.Drawing.Point(503, 46);
			this.BtnOpenJsonFile.Name = "BtnOpenJsonFile";
			this.BtnOpenJsonFile.Size = new System.Drawing.Size(75, 23);
			this.BtnOpenJsonFile.TabIndex = 2;
			this.BtnOpenJsonFile.Text = "Open";
			this.BtnOpenJsonFile.UseVisualStyleBackColor = true;
			this.BtnOpenJsonFile.Click += new System.EventHandler(this.BtnOpenJsonFile_Click);
			// 
			// groupBox1
			// 
			this.groupBox1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
			this.groupBox1.Controls.Add(this.EditPanel);
			this.groupBox1.Controls.Add(this.TvDataShow);
			this.groupBox1.Location = new System.Drawing.Point(13, 124);
			this.groupBox1.Name = "groupBox1";
			this.groupBox1.Size = new System.Drawing.Size(565, 271);
			this.groupBox1.TabIndex = 3;
			this.groupBox1.TabStop = false;
			this.groupBox1.Text = "System";
			// 
			// TvDataShow
			// 
			this.TvDataShow.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
			this.TvDataShow.Location = new System.Drawing.Point(6, 19);
			this.TvDataShow.Name = "TvDataShow";
			this.TvDataShow.Size = new System.Drawing.Size(401, 246);
			this.TvDataShow.TabIndex = 0;
			// 
			// DialogOpenFile
			// 
			this.DialogOpenFile.CheckFileExists = false;
			this.DialogOpenFile.CheckPathExists = false;
			this.DialogOpenFile.Filter = "Json file (*.json)|*.json";
			// 
			// BtnLoadData
			// 
			this.BtnLoadData.Location = new System.Drawing.Point(76, 82);
			this.BtnLoadData.Name = "BtnLoadData";
			this.BtnLoadData.Size = new System.Drawing.Size(157, 36);
			this.BtnLoadData.TabIndex = 4;
			this.BtnLoadData.Text = "Load data";
			this.BtnLoadData.UseVisualStyleBackColor = true;
			this.BtnLoadData.Click += new System.EventHandler(this.BtnLoadData_Click);
			// 
			// label3
			// 
			this.label3.AutoSize = true;
			this.label3.Location = new System.Drawing.Point(3, 13);
			this.label3.Name = "label3";
			this.label3.Size = new System.Drawing.Size(89, 13);
			this.label3.TabIndex = 0;
			this.label3.Text = "File/Folder name:";
			// 
			// TxtName
			// 
			this.TxtName.Location = new System.Drawing.Point(6, 29);
			this.TxtName.Name = "TxtName";
			this.TxtName.ReadOnly = true;
			this.TxtName.Size = new System.Drawing.Size(134, 20);
			this.TxtName.TabIndex = 1;
			// 
			// label4
			// 
			this.label4.AutoSize = true;
			this.label4.Location = new System.Drawing.Point(10, 59);
			this.label4.Name = "label4";
			this.label4.Size = new System.Drawing.Size(63, 13);
			this.label4.TabIndex = 2;
			this.label4.Text = "Text display";
			// 
			// txtDisplay
			// 
			this.txtDisplay.Location = new System.Drawing.Point(6, 76);
			this.txtDisplay.Name = "txtDisplay";
			this.txtDisplay.Size = new System.Drawing.Size(134, 20);
			this.txtDisplay.TabIndex = 3;
			// 
			// EditPanel
			// 
			this.EditPanel.Controls.Add(this.txtDisplay);
			this.EditPanel.Controls.Add(this.label4);
			this.EditPanel.Controls.Add(this.TxtName);
			this.EditPanel.Controls.Add(this.label3);
			this.EditPanel.Location = new System.Drawing.Point(413, 19);
			this.EditPanel.Name = "EditPanel";
			this.EditPanel.Size = new System.Drawing.Size(143, 107);
			this.EditPanel.TabIndex = 1;
			// 
			// Form1
			// 
			this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
			this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
			this.ClientSize = new System.Drawing.Size(590, 407);
			this.Controls.Add(this.BtnLoadData);
			this.Controls.Add(this.groupBox1);
			this.Controls.Add(this.BtnOpenJsonFile);
			this.Controls.Add(this.BtnSelectDataPath);
			this.Controls.Add(this.TxtJsonPath);
			this.Controls.Add(this.TxtDataPath);
			this.Controls.Add(this.label2);
			this.Controls.Add(this.label1);
			this.Name = "Form1";
			this.Text = "Form1";
			this.groupBox1.ResumeLayout(false);
			this.EditPanel.ResumeLayout(false);
			this.EditPanel.PerformLayout();
			this.ResumeLayout(false);
			this.PerformLayout();

		}

		#endregion

		private System.Windows.Forms.Label label1;
		private System.Windows.Forms.TextBox TxtDataPath;
		private System.Windows.Forms.Button BtnSelectDataPath;
		private System.Windows.Forms.Label label2;
		private System.Windows.Forms.TextBox TxtJsonPath;
		private System.Windows.Forms.Button BtnOpenJsonFile;
		private System.Windows.Forms.GroupBox groupBox1;
		private System.Windows.Forms.TreeView TvDataShow;
		private System.Windows.Forms.OpenFileDialog DialogOpenFile;
		private System.Windows.Forms.FolderBrowserDialog DialogSelectFolder;
		private System.Windows.Forms.Button BtnLoadData;
		private System.Windows.Forms.Panel EditPanel;
		private System.Windows.Forms.TextBox txtDisplay;
		private System.Windows.Forms.Label label4;
		private System.Windows.Forms.TextBox TxtName;
		private System.Windows.Forms.Label label3;
	}
}

