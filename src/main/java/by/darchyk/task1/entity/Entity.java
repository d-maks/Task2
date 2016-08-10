package by.darchyk.task1.entity;

public class Entity {

	private String className;
	private String keyword;

	public Entity() {
		super();
	}

	public Entity(int id, String className, String keyword) {
		super();
		this.className = className;
		this.keyword = keyword;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeywords(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((className == null) ? 0 : className.hashCode());
		result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
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
		Entity other = (Entity) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (keyword == null) {
			if (other.keyword != null)
				return false;
		} else if (!keyword.equals(other.keyword))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Entity [className=" + className + ", keyword=" + keyword + "]";
	}

}
