using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API_Cash_MachineV2.Controllers
{
    internal class StringConnection
    {
        private String _nomConnection = "DBConnection";
        private String _connectionString = "Data Source=MSI;Initial Catalog=cashDb;Integrated Security=True";
        private String _providerName = "System.Data.SqlClient";
        public String NomConnection
        {
            get { return _nomConnection; }
            set { _nomConnection = value; }
        }
        public String ConnectionString
        {
            get { return _connectionString; }
            set { _connectionString = value; }
        }
        public String ProviderName
        {
            get { return _providerName; }
            set { _providerName = value; }
        }
    }
}