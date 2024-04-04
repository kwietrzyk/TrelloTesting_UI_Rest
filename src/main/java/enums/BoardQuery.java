package enums;

// Acceptable query values: https://developer.atlassian.com/cloud/trello/rest/api-group-boards/#api-boards-id-put
public enum BoardQuery {
    NAME("name"),
    DESC("desc"),
    PREFS_SELF_JOIN("prefs/selfJoin"),
    PREFS_CARD_COVERS("prefs/cardCovers"),
    PREFS_HIDE_VOTES("prefs/hideVotes"),
    PREFS_INVITATIONS("prefs/invitations"),
    PREFS_VOTING("prefs/voting"),
    PREFS_COMMENTS("prefs/comments"),
    PREFS_BACKGROUND("prefs/background"),
    PREFS_CARD_AGING("prefs/cardAging"),
    PREFS_CALENDAR_FEED_ENABLED("prefs/calendarFeedEnabled"),
    LABEL_NAMES_GREEN("labelNames/green"),
    LABEL_NAMES_YELLOW("labelNames/yellow"),
    LABEL_NAMES_ORANGE("labelNames/orange"),
    LABEL_NAMES_RED("labelNames/red"),
    LABEL_NAMES_PURPLE("labelNames/purple"),
    LABEL_NAMES_BLUE("labelNames/blue");

    private final String param;

    BoardQuery(String value) {
        this.param = value;
    }

    public String getParam() {
        return param;
    }
}
