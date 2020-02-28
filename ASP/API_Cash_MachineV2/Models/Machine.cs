using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API_Cash_MachineV2.Models
{
    public class Machine
    {
        #region proriete

        private int _id;
        private int _idParking;


        #endregion

        #region getter setter
        public int Id
        {
            get { return _id; }
            set { _id = value; }
        }
        public int IdParking
        {
            get { return _idParking; }
            set { _idParking = value; }
        }
        #endregion

        #region constructeur(s)
        public Machine(int id, int maxCapacity, int currentCapacity, int idParking)
        {
            Id = id;
            IdParking = idParking;
        }
        public Machine()
        {
        }

        public override bool Equals(object obj)
        {
            return obj is Machine machine &&
                   Id == machine.Id;
        }
        #endregion


    }
}

 