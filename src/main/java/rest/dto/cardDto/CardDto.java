package rest.dto.cardDto;

import java.io.Serializable;
import java.util.ArrayList;

public class CardDto implements Serializable {
    public String id;
    public Badges badges;
    public ArrayList<Object> checkItemStates;
    public boolean closed;
    public boolean dueComplete;
    public String dateLastActivity;
    public String desc;
    public DescData descData;
    public Object due;
    public Object dueReminder;
    public Object email;
    public String idBoard;
    public ArrayList<Object> idChecklists;
    public String idList;
    public ArrayList<Object> idMembers;
    public ArrayList<Object> idMembersVoted;
    public int idShort;
    public Object idAttachmentCover;
    public ArrayList<Object> labels;
    public ArrayList<Object> idLabels;
    public boolean manualCoverAttachment;
    public String name;
    public int pos;
    public String shortLink;
    public String shortUrl;
    public Object start;
    public boolean subscribed;
    public String url;
    public Cover cover;
    public boolean isTemplate;
    public Object cardRole;

    @Override
    public String toString() {
        return "CardDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

class AttachmentsByType implements Serializable {
    public Trello trello;
}

class Badges implements Serializable {
    public AttachmentsByType attachmentsByType;
    public boolean location;
    public int votes;
    public boolean viewingMemberVoted;
    public boolean subscribed;
    public String fogbugz;
    public int checkItems;
    public int checkItemsChecked;
    public Object checkItemsEarliestDue;
    public int comments;
    public int attachments;
    public boolean description;
    public Object due;
    public boolean dueComplete;
    public Object start;
}

class Cover implements Serializable {
    public Object idAttachment;
    public Object color;
    public Object idUploadedBackground;
    public String size;
    public String brightness;
    public Object idPlugin;
}

class DescData implements Serializable {
    public Emoji emoji;
}

class Emoji implements Serializable {
}

class Trello implements Serializable {
    public int board;
    public int card;
}

