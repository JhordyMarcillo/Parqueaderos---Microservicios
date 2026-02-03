package ec.edu.espe.zone_core.dto;

import ec.edu.espe.zone_core.model.SpaceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data //creación aut. getters y stters
@Builder //instanciación de objetos
@NoArgsConstructor
@AllArgsConstructor
public class SpacesRequestDto {
        private String codigo;
        private SpaceStatus status;
        private boolean isReserved;
        private Integer priority;
        private UUID idZone;

}
