package db;

/**
 * Created by thomashli on 2/26/17.
 */
public enum Data {
    FLOAT, STRING, INT;

    public static Data operate(Data a, Data b) {
        switch (a) {
            case FLOAT:
                switch (b) {
                    case INT:
                        return FLOAT;
                    case FLOAT:
                        return FLOAT;
                    default:
                        throw new IllegalOperationException("Cannot combine class "
                                + a.toString() + " and " + b.toString() + " in operation.");
                }
            case INT:
                switch (b) {
                    case INT:
                        return INT;
                    case FLOAT:
                        return FLOAT;
                    default:
                        throw new IllegalOperationException("Cannot combine class "
                                + a.toString() + " and " + b.toString() + " in operation.");
                }
            case STRING:
                switch (b) {
                    case STRING:
                        return STRING;
                    default:
                        throw new IllegalOperationException("Cannot combine class "
                                + a.toString() + " and " + b.toString() + " in operation.");
                }
            default:
                throw new IllegalOperationException("Unrecognized class");

        }
    }

    public static Data type(String s) {
        if (s.matches(("^-?\\d+$"))) {
            return Data.INT;
        } else if (s.matches("[-+]?[0-9]*\\.[0-9]*")) {
            return Data.FLOAT;
        } else if (s.matches("^'.*'$")) {
            return Data.STRING;
        }
        return null;
    }

}
