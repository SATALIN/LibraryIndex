/**
 * 
 */
package by.satalin.library.datamodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.log4j.Logger;

/**
 * @author SATALIN entity book
 * 
 */
@Entity
@Table(name = "book")
@org.hibernate.annotations.BatchSize(size = 30)
public class Book extends AbstractEntity implements Serializable {
	private static final Logger log = Logger.getLogger(Book.class);
	private Long id;
	private String author;
	private String title;
	private Date date;
	private Long abonentId;

	public Book() {
	}

	public Book(String author, String title, Date date, Long abonentId) {
		this.author = author;
		this.title = title;
		this.date = date;
		this.abonentId = abonentId;
	}

	@Id
	@GeneratedValue
	@Column(name = "BOOK_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "AUTHOR", nullable = false, length = 100)
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "TITLE", nullable = false, length = 100)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "ABONENT", length = 100)
	public Long getAbonent() {
		return abonentId;
	}

	public void setAbonent(Long abonentId) {
		this.abonentId = abonentId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((abonentId == null) ? 0 : abonentId.hashCode());
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (abonentId == null) {
			if (other.abonentId != null)
				return false;
		} else if (!abonentId.equals(other.abonentId))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title
				+ ", date=" + date + ", abonentId=" + abonentId + "]";
	}

}
