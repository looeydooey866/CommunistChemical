package src;

import src.Exceptions.DomainException;

public enum MolecularGeometry {
    NONE,
    LINEAR,
    BENT,
    TRIGONAL_PLANAR,
    TETRAHEDRAL,
    TRIGONAL_PYRAMIDAL,
    TRIGONAL_BIPYRAMIDAL,
    SEESAW,
    T_SHAPED,
    OCTAHEDRAL,
    SQUARE_PYRAMIDAL,
    SQUARE_PLANAR;

    public static MolecularGeometry get(int loneDomains, int sigmaPairs){
        int totalDomains = loneDomains + sigmaPairs;
        if (totalDomains - loneDomains < 2){
            return NONE;
        }
        return switch(totalDomains){
            case 1 -> NONE;
            case 2 -> {
                yield switch(loneDomains){
                    case 0 -> LINEAR;
                    default -> throw new DomainException(String.format("Error, %d is an invalid number of domains.", totalDomains));
                };
            }
            case 3 -> {
                yield switch(loneDomains){
                    case 0 -> TRIGONAL_PLANAR;
                    case 1 -> BENT;
                    default -> throw new DomainException(String.format("Error, %d is an invalid number of domains.", totalDomains));
                };
            }
            case 4 -> {
                yield switch(loneDomains){
                    case 0 -> TETRAHEDRAL;
                    case 1 -> TRIGONAL_PYRAMIDAL;
                    case 2 -> BENT;
                    default -> throw new DomainException(String.format("Error, %d is an invalid number of domains.", totalDomains));
                };
            }
            case 5 -> {
                yield switch(loneDomains){
                    case 0 -> TRIGONAL_BIPYRAMIDAL;
                    case 1 -> SEESAW;
                    case 2 -> T_SHAPED;
                    case 3 -> LINEAR;
                    default -> throw new DomainException(String.format("Error, %d is an invalid number of domains.", totalDomains));
                };
            }
            case 6 -> {
                yield switch(loneDomains){
                    case 0 -> OCTAHEDRAL;
                    case 1 -> SQUARE_PYRAMIDAL;
                    case 2 -> SQUARE_PLANAR;
                    case 3 -> T_SHAPED;
                    case 4 -> LINEAR;
                    default -> throw new DomainException(String.format("Error, %d is an invalid number of domains.", totalDomains));
                };
            }
            default -> throw new DomainException(String.format("Error, %d is an invalid number of domains.", totalDomains));
        };
    }
}
