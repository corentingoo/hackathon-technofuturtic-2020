using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Threading.Tasks;
using API_Cash_MachineV2.Infrastructure;
using API_Cash_MachineV2.Models;
using API_Cash_MachineV2.ToolBox;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace API_Cash_MachineV2.Controllers
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
        [Route("[controller]/{idTypeCash:int}")]
        public Cash GetById(int idTypeCash) {
            Command cmd = new Command("Select * from T_cash WHERE ID_cash = "+ idTypeCash);

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

    }
}