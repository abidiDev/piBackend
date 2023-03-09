package com.spring.pi.payload.request;

import com.spring.pi.entities.HouseBuilding;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddConstructionRequeste {
    private Long idActor;
    private Long idAgenceConstruction;
    private HouseBuilding houseBuilding;
    private Date dateLimit;
    private Date DateDebut;
    private Long prixC;
    private Integer date;
    private Integer nbredetranche;
    private Float mensionalite;
}
