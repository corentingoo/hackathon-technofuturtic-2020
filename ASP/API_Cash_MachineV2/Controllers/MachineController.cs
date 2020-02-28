using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using API_Cash_MachineV2.Infrastructure;
using API_Cash_MachineV2.Models;
using API_Cash_MachineV2.ToolBox;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace API_Cash_MachineV2.Controllers
{
    [ApiController]
    public class MachineController : ControllerBase
    {
        private readonly Connection _connection;
        public MachineController(Connection connection)
        {
            _connection = connection;
        }

        [HttpGet]
        [Route("[controller]")]
        public IEnumerable<Machine> Get() {
            Command cmd = new Command("Select * from T_machines");

            DataTable dt = _connection.GetDataTable(cmd);
            List<Machine> result = new List<Machine>();

            foreach (DataRow dr in dt.Rows)
            {

                Machine m = dr.toMachine();
                result.Add(m);
            }
            return result;
            
        }
        [HttpGet]
        [Route("[controller]/{id:int}")]
        public Machine GetById(int id) {
                Command cmd = new Command("Select * from T_machines WHERE ID_machine = " + id);

                DataTable dt = _connection.GetDataTable(cmd);
                DataRow dr = dt.Rows[0];
                Machine result = dr.toMachine();
                return result;
        }
    }
}