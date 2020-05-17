package com.dummy.myerp.model.bean.comptabilite;

import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dummy.myerp.technical.log.message.DebugMessage;
import com.dummy.myerp.technical.log.message.EntreeMessage;
import com.dummy.myerp.technical.log.message.SortieMessage;

/**
 * Bean représentant un Journal Comptable
 */
public class JournalComptable {

	/** Logger Log4j pour la classe */
	private static final Logger LOGGER = LogManager.getLogger(JournalComptable.class);

	// ==================== Attributs ====================
	/** Code du journal comptable */
	@NotNull
	@Size(min = 1, max = 5)
	private String code;

	/** Libellé du journal comptable */
	@NotNull
	@Size(min = 1, max = 150)
	private String libelle;

	// ==================== Constructeurs ====================
	/**
	 * Constructeur.
	 */
	public JournalComptable() {
	}

	/**
	 * Constructeur.
	 *
	 * @param pCode    - Code du journal comptable
	 * @param pLibelle - Libellé du journal comptable
	 */
	public JournalComptable(String pCode, String pLibelle) {
		code = pCode;
		libelle = pLibelle;
	}

	// ==================== Getters/Setters ====================
	/**
	 * Renvoie un string représentant le code du journal comptable.
	 * 
	 * @return {@link String} représentant le code du journal comptable
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Initialise le code du journal comptable
	 * 
	 * @param pCode - Code du journal comptable
	 */
	public void setCode(String pCode) {
		code = pCode;
	}

	/**
	 * Renvoie un string représentant le libellé du journal comptable
	 * 
	 * @return {@link String} représentant le libellé du journal comptable
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Initialise le libellé du journal comptable
	 * 
	 * @param pLibelle - Libellé du journal comptable
	 */
	public void setLibelle(String pLibelle) {
		libelle = pLibelle;
	}

	// ==================== Méthodes ====================
	/**
	 * {@inheritDoc}
	 * 
	 * @see StringBuilder
	 */
	@Override
	public String toString() {
		final StringBuilder vStB = new StringBuilder(this.getClass().getSimpleName());
		final String vSEP = ", ";
		vStB.append("{").append("code='").append(code).append('\'').append(vSEP).append("libelle='").append(libelle)
				.append('\'').append("}");
		return vStB.toString();
	}

	// ==================== Méthodes STATIC ====================
	/**
	 * Renvoie le {@link JournalComptable} de code {@code pCode} s'il est présent
	 * dans la liste
	 *
	 * @param pList - la liste où chercher le {@link JournalComptable}
	 * @param pCode - le code du {@link JournalComptable} à chercher
	 * @return {@link JournalComptable} ou {@code null}
	 */
	public static JournalComptable getByCode(List<? extends JournalComptable> pList, String pCode) {
		LOGGER.trace(new EntreeMessage());
		LOGGER.debug(new DebugMessage("List<? extends CompteComptable> pList", pList));
		LOGGER.debug(new DebugMessage("String pCode", pCode));
		JournalComptable vRetour = null;
		if (pList != null) {
			for (JournalComptable vBean : pList) {
				if (vBean != null && Objects.equals(vBean.getCode(), pCode)) {
					vRetour = vBean;
					break;
				}
			}
		}
		LOGGER.debug(new DebugMessage("JournalComptable vRetour", vRetour));
		LOGGER.trace(new SortieMessage());
		return vRetour;
	}
}
