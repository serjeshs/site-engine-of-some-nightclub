package by.havefun.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The persistent class for the appuser_like_event database table.
 * 
 */
@Entity
@Table(name = "appuser_like_event")
@NamedQuery(name = "AppUserLikeEvent.findAll", query = "SELECT a FROM AppUserLikeEvent a")
public class AppUserLikeEvent implements Serializable {
    
    private static final long serialVersionUID = 338L;
    public static final String COL_APPUSER_ID = "appuserId";
    public static final String COL_EVENT_ID = "eventId";
    public static final String COL_STATUS = "status";

	public static final int NOTHING = 0;
	public static final int BETHERE = 1;
	public static final int MAYATTEND = 2;
	public static final int DIZLIKE = 3;
    
    @Id
    @Column(name = "appuser_id", insertable = false, updatable = false)
    private int appuserId;

    @Id
    @Column(name = "event_id", insertable = false, updatable = false)
    private int eventId;

    @Column(name = "STATUS")
    private int status;

    // bi-directional many-to-one association to AppUser
    @ManyToOne(fetch = FetchType.LAZY)
    private AppUser appuser;

    // bi-directional many-to-one association to Event
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    public AppUserLikeEvent() {
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AppUser getAppuser() {
        return this.appuser;
    }

    public void setAppuser(AppUser appuser) {
        this.appuser = appuser;
    }

    public Event getEvent() {
        return this.event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getAppuserId() {
        return this.appuserId;
    }

    public void setAppuserId(int appuserId) {
        this.appuserId = appuserId;
    }

    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + appuserId;
        result = prime * result + eventId;
        result = prime * result + status;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AppUserLikeEvent other = (AppUserLikeEvent) obj;
        if (appuserId != other.appuserId)
            return false;
        if (eventId != other.eventId)
            return false;
        if (status != other.status)
            return false;
        return true;
    }

}
