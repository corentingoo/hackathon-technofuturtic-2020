using API_Cash_MachineV2.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace API_Cash_MachineV2.Dtos
{
    public class MachineListDto
    {
        public int id { get; set; }
        public int idParking { get; set; }
        public List<Cash> cashes { get; set; }

        public static MachineListDto from(Machine machine, List<Cash> caches)
        {
            MachineListDto dto = new MachineListDto { id = machine.Id, idParking = machine.IdParking , cashes = caches };

            return dto;
        }
    }
}
