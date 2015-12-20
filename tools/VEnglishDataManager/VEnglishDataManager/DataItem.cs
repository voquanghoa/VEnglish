using System.Collections.Generic;
using System.Runtime.Serialization;

namespace VEnglishDataManager
{
	[DataContract]
	public class DataItem
	{
		[DataMember(Name = "fileName", IsRequired = true)]
		public string FileName
		{
			get; set;
		}

		[DataMember(Name = "display", IsRequired = true)]
		public string Display
		{
			get; set;
		}

		[DataMember(Name = "children", IsRequired = false)]
		public IList<DataItem> Children
		{
			get; set;
		}
	}
}
