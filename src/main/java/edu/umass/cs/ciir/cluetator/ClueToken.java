package edu.umass.cs.ciir.cluetator;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of one token in a document, which optionally has one (or more) entity annotations.
 */
public class ClueToken {
    // j		J		J		J		/m/0d783k		category:iso basic latin letters		/user/tsturge/language/letter	/user/tsturge/language/topic

    private String normToken;
    private String capToken;
    private List<String> entityMentions;
    private List<String> entityWikiTitles;
    private List<String> entityFreebaseMids;
    private List<String> wikiCategories;
    private List<String> freebaseTypes;


    public ClueToken(String normToken, String capToken, List<String> entityMentions, List<String> entityWikiTitles, List<String> entityFreebaseMids, List<String> wikiCategories, List<String> freebaseTypes) {
        this.normToken = normToken;
        this.capToken = capToken;
        this.entityMentions = entityMentions;
        this.entityWikiTitles = entityWikiTitles;
        this.entityFreebaseMids = entityFreebaseMids;
        this.wikiCategories = wikiCategories;
        this.freebaseTypes = freebaseTypes;
    }

    public ClueToken(String normToken, String capToken) {
        this.normToken = normToken;
        this.capToken = capToken;
        this.entityMentions = Collections.emptyList();
        this.entityWikiTitles = Collections.emptyList();
        this.entityFreebaseMids = Collections.emptyList();
        this.wikiCategories = Collections.emptyList();
        this.freebaseTypes = Collections.emptyList();
    }

    public String getNormToken() {
        return normToken;
    }

    public String getCapToken() {
        return capToken;
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
}
