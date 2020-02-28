using API_CashMachine.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace API_CashMachine.Infrastructure
{
    public static class Mapper
    {
        #region machine 
        public static Machine toMachine(this DataRow m)
        {
            return new Machine()
            {
                Id = (int)m["IDMachine"],
                MaxCapacity = (int)m["max_capacity"],
                ThisCapacity = (int)m["this_capacity"]
            };
        }
        #endregion
    }
}