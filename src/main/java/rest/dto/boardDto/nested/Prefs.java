package rest.dto.boardDto.nested;

import java.io.Serializable;
import java.util.ArrayList;

public class Prefs implements Serializable {
    public String permissionLevel;
    public boolean hideVotes;
    public String voting;
    public String comments;
    public String invitations;
    public boolean selfJoin;
    public boolean cardCovers;
    public boolean cardCounts;
    public boolean isTemplate;
    public String cardAging;
    public boolean calendarFeedEnabled;
    public ArrayList<Object> hiddenPluginBoardButtons;
    public ArrayList<SwitcherView> switcherViews;
    public String background;
    public String backgroundColor;
    public Object backgroundImage;
    public boolean backgroundTile;
    public String backgroundBrightness;
    public Object sharedSourceUrl;
    public Object backgroundImageScaled;
    public String backgroundBottomColor;
    public String backgroundTopColor;
    public boolean canBePublic;
    public boolean canBeEnterprise;
    public boolean canBeOrg;
    public boolean canBePrivate;
    public boolean canInvite;
}
