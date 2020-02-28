using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace API_CashMachine.Models
{
    public class Machine
    {
        #region proriete

        private int _id;
        private int _maxCapacity;
        private int _thisCapacity;

        #endregion

        #region getter setter
        public int Id
        {
            get { return _id; }
            set { _id = value; }
        }
        public int MaxCapacity
        {
            get { return _maxCapacity; }
            set { _maxCapacity = value; }
        }
        public int ThisCapacity
        {
            get { return _thisCapacity; }
            set { _thisCapacity = value; }
        }
        #endregion

    }
}