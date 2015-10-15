package by.havefun.utils.view;

public class MenuItems {
	
	private String name;
	private String link;
	
	
	public MenuItems(String link, String name) {
	    super();
	    this.name = name;
	    this.link = link;
    }
	
	public String getName() {
	    return name;
    }
	public void setName(String name) {
	    this.name = name;
    }
	public String getLink() {
	    return link;
    }
	public void setLink(String link) {
	    this.link = link;
    }

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((link == null) ? 0 : link.hashCode());
	    result = prime * result + ((name == null) ? 0 : name.hashCode());
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
	    MenuItems other = (MenuItems) obj;
	    if (link == null) {
		    if (other.link != null)
			    return false;
	    } else if (!link.equals(other.link))
		    return false;
	    if (name == null) {
		    if (other.name != null)
			    return false;
	    } else if (!name.equals(other.name))
		    return false;
	    return true;
    }

	@Override
    public String toString() {
	    return "MenuItems [name=" + name + ", link=" + link + "]";
    }

}
