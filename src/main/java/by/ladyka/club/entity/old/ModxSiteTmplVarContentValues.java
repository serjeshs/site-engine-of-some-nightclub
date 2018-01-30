package by.ladyka.club.entity.old;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.persistence.*;

@Entity
@Table(name = "modx_site_tmplvar_contentvalues")
@Getter
@Setter
public class ModxSiteTmplVarContentValues {

    @Id
    private Long id;
    private Long tmplvarid;

    @ManyToOne
    @JoinColumn(name = "contentid")
    private ModxSiteContent content;
    private String value;

}
