package edu.umass.cs.ciir.cluetator;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of one token in a document, which optionally has one (or more) entity annotations.
 */
public class ClueToken {
    // j		J		J		J		/m/0d783k		category:iso basic latin letters		/user/tsturge/language/letter	/user/tsturge/language/topic

    private String nlpToken;
    private String irToken;
    private List<String> entityMentions;
    private List<String> entityWikiTitles;
    private List<String> entityFreebaseMids;
    private List<String> wikiCategories;
    private List<String> freebaseTypes;
    private boolean startOfSentence;


    public ClueToken(String nlpToken, String irToken, List<String> entityMentions, List<String> entityWikiTitles, List<String> entityFreebaseMids, List<String> wikiCategories, List<String> freebaseTypes, boolean startOfSentence) {
        this.nlpToken = nlpToken;
        this.irToken = irToken;
        this.entityMentions = entityMentions;
        this.entityWikiTitles = entityWikiTitles;
        this.entityFreebaseMids = entityFreebaseMids;
        this.wikiCategories = wikiCategories;
        this.freebaseTypes = freebaseTypes;
        this.startOfSentence = startOfSentence;
    }

    public ClueToken(String nlpToken, String irToken, boolean startOfSentence) {
        this.nlpToken = nlpToken;
        this.irToken = irToken;
        this.startOfSentence = startOfSentence;
        this.entityMentions = Collections.emptyList();
        this.entityWikiTitles = Collections.emptyList();
        this.entityFreebaseMids = Collections.emptyList();
        this.wikiCategories = Collections.emptyList();
        this.freebaseTypes = Collections.emptyList();
    }

    public String getNlpToken() {
        return nlpToken;
    }

    public String getIrToken() {
        return irToken;
    }

    public List<String> getEntityMentions() {
        return entityMentions;
    }

    public List<String> getEntityWikiTitles() {
        return entityWikiTitles;
    }

    public List<String> getEntityFreebaseMids() {
        return entityFreebaseMids;
    }

    public List<String> getWikiCategories() {
        return wikiCategories;
    }

    public List<String> getFreebaseTypes() {
        return freebaseTypes;
    }

    public boolean hasEntityAnnotation() {
        return !entityMentions.isEmpty();
    }

    public boolean isStartOfSentence() {
        return startOfSentence;
    }
}
