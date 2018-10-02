package org.bechynak.meals.services;

import org.jsoup.nodes.Document;

public interface DocumentsService {
    Document getDoc(String url);
}
