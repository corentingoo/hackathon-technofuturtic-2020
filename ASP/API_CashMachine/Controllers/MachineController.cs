using API_CashMachine.Infrastructure;
using API_CashMachine.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.Http;
using System.Web.Http.Cors;
using ToolBox;

namespace API_CashMachine.Controllers
{
    [EnableCors(origins:"*",headers:"*",methods:"*")]
    public class MachineController : ApiController
    {
        public static ConnectionStringSettings GetConnectionStrings(String name) {
            ConnectionStringSettings connections = ConfigurationManager.ConnectionStrings[name];
            return connections;
        }
        // GET: api/Machine
        [Route("api/Machine")]
        public List<Machine> GetAll() {
            Command cmd = new Command("Select * from Machine");
            Connection con = new Connection(GetConnectionStrings("DBConnection").ProviderName, GetConnectionStrings("DBConnection").ConnectionString);

            DataTable dt = con.GetDataTable(cmd);
            List<Machine> result = new List<Machine>();

            foreach (DataRow dr in dt.Rows)
            {

                Machine m = dr.toMachine();
                result.Add(m);
            }
            return result;
        }
    }
}