package local.ice.service;


public enum CreditRoleFilter {

    PRINCIPAL("Main Artist");

    private final String creditRole;

    CreditRoleFilter(String creditRole) {
        this.creditRole = creditRole;
    }

    //~

    public String creditRole() {
        return creditRole;
    }

    public static CreditRoleFilter from(String value) {
        for (CreditRoleFilter filter : values()) {
            if (filter.name().equalsIgnoreCase(value)) {
                return filter;
            }
        }
        throw new IllegalArgumentException("Unknown role filter; filter=" + value);
    }
}
