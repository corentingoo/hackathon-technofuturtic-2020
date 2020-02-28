using System.Collections.Generic;
using System.Data;
using API_Cash_MachineV3.Infrastructure;
using API_Cash_MachineV3.Models;
using API_Cash_MachineV3.ToolBox;
using Microsoft.AspNetCore.Mvc;

namespace API_Cash_MachineV3.Controllers
{
    [ApiController]
    public class CashController : ControllerBase
    {
        private readonly Connection _connection;
        public CashController(Connection connection)
        {
            _connection = connection;
        }

        [HttpGet]
        [Route("[controller]")]
        public IEnumerable<Cash> GetAll()
        {
            Command cmd = new Command("Select * from T_cash");

            DataTable dt = _connection.GetDataTable(cmd);
            List<Cash> result = new List<Cash>();

            foreach (DataRow dr in dt.Rows)
            {

                Cash ct = dr.toCashType();
                result.Add(ct);
            }
            return result;
        }

        [HttpGet]
        [Route("[controller]/{id:int}")]
        public Cash GetById(int id)
        {
            Command cmd = new Command("Select * from T_cash WHERE ID_cash = " + id);

            DataTable dt = _connection.GetDataTable(cmd);
            DataRow dr = dt.Rows[0];
            Cash result = dr.toCashType();
            return result;

        }
        [HttpGet]
        [Route("[controller]/machine/{idMachine}")]
        public IEnumerable<Cash> GetBymachine(int idMachine)
        {
            Command cmd = new Command("Select * from T_cash WHERE FK_machine = " + idMachine);

            DataTable dt = _connection.GetDataTable(cmd);
            List<Cash> result = new List<Cash>();

            foreach (DataRow dr in dt.Rows)
            {

                Cash ct = dr.toCashType();
                result.Add(ct);
            }
            return result;
        }
        [HttpPost]
        [Route("[controller]")]
        public Cash Post(Cash c) {
            Command cmd = new Command(@"UPDATE T_cash
            SET Current_capacity =@currentCapacity
            WHERE ID_cash = @id");
            cmd.AddParameter("currentCapacity", c.CurrentCapacity);
            cmd.AddParameter("id", c.Id);
            Connection con = _connection;
            Cash ch = (Cash)con.ExecuteScalar(cmd);
            c = ch;
            return c;
        }
    }
}