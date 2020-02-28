using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using API_Cash_MachineV3.Dtos;
using API_Cash_MachineV3.Infrastructure;
using API_Cash_MachineV3.Models;
using API_Cash_MachineV3.ToolBox;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace API_Cash_MachineV3.Controllers
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
        public IEnumerable<MachineListDto> Get()
        {
            Command cmd = new Command("Select * from T_machines m join T_cash c on m.ID_machine = c.FK_Machine");

            DataTable dt = _connection.GetDataTable(cmd);
            List<MachineListDto> result = new List<MachineListDto>();
            Dictionary<Machine, List<Cash>> machineCashes = new Dictionary<Machine, List<Cash>>();

            foreach (DataRow dr in dt.Rows)
            {

                Machine m = dr.toMachine();
                Cash c = dr.toCashType();

                if (!machineCashes.ContainsKey(m))
                {
                    machineCashes.Add(m, new List<Cash>());
                }
                if (c.CurrentCapacity >= (c.MaxCapacity * 0.8) || c.CurrentCapacity <= (c.MaxCapacity*0.2))
                {
                    machineCashes[m].Add(c);
                }
            }

            foreach (var machine in machineCashes)
            {
                result.Add(MachineListDto.from(machine.Key, machine.Value));
            }

            return result;

        }
        [HttpGet]
        [Route("[controller]/{id:int}")]
        public Machine GetById(int id)
        {
            Command cmd = new Command("Select * from T_machines WHERE ID_machine = " + id);

            DataTable dt = _connection.GetDataTable(cmd);
            DataRow dr = dt.Rows[0];
            Machine result = dr.toMachine();
            return result;
        }
    }
}