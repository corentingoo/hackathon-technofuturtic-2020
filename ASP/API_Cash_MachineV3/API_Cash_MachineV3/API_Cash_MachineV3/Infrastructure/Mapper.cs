using API_Cash_MachineV3.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Threading.Tasks;

namespace API_Cash_MachineV3.Infrastructure
{
    public static class Mapper
    {
        #region machine 
        public static Machine toMachine(this DataRow m)
        {
            return new Machine()
            {
                Id = (int)m["ID_machine"],
                IdParking = (int)m["ID_parking"]
            };
        }
        #endregion
        #region cashType
        public static Cash toCashType(this DataRow ct)
        {
            return new Cash()
            {
                Id = (int)ct["ID_cash"],
                IdTypeCash = (int)ct["ID_type_cash"],
                TypeCash = (string)ct["Type_cash"],
                MaxCapacity = (int)ct["Max_capacity"],
                CurrentCapacity = (int)ct["Current_capacity"],
                IdMachine = (int)ct["FK_machine"]
            };
        }
        #endregion
    }
}
