using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API_Cash_MachineV3.Models
{
    public class Cash
    {
        private int _id;//1-coinin1 2-coinin2 3-coinin3 4-coinin4 5-coinin5 6-coinOut1 7-coinOut2 8-coinOut3 9-coinOut4 10-coinOut5 11-note1 12-note2 13-note3 14-note4
        private int _idTypeCash;
        private String _typeCash;
        private int _maxCapacity;
        private int _currentCapacity;
        private int _idMachine;

        public Cash()
        {
        }

        public Cash(int _id, int _idTypeCash, String _typeCash, int _maxCapacity, int _currentCapacity, int _idMachine)
        {
            this._id = _id;
            this._idTypeCash = _idTypeCash;
            this._typeCash = _typeCash;
            this._maxCapacity = _maxCapacity;
            this._currentCapacity = _currentCapacity;
            this._idMachine = _idMachine;
        }


        public int Id
        {
            get { return _id; }
            set { _id = value; }
        }
        public int IdTypeCash
        {
            get { return _idTypeCash; }
            set { _idTypeCash = value; }
        }
        public String TypeCash
        {
            get { return _typeCash; }
            set { _typeCash = value; }
        }
        public int MaxCapacity
        {
            get { return _maxCapacity; }
            set { _maxCapacity = value; }
        }
        public int CurrentCapacity
        {
            get { return _currentCapacity; }
            set { _currentCapacity = value; }
        }
        public int IdMachine
        {
            get { return _idMachine; }
            set { _idMachine = value; }
        }

        public override bool Equals(object obj)
        {
            return obj is Cash cash &&
                   Id == cash.Id &&
                   IdTypeCash == cash.IdTypeCash &&
                   IdMachine == cash.IdMachine;
        }
    }
}
