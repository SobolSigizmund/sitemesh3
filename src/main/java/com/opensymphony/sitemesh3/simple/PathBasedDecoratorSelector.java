package com.opensymphony.sitemesh3.simple;

import com.opensymphony.sitemesh3.DecoratorSelector;
import com.opensymphony.sitemesh3.SiteMeshContext;
import com.opensymphony.sitemesh3.content.ContentProperty;

import java.io.IOException;

/**
 * {@link DecoratorSelector} implementation that selects a decorator based on the
 * incoming {@link SiteMeshContext#getRequestPath()} and the mappings setup.
 *
 * <h3>Example</h3>
 * <pre>
 * DecoratorSelector selector = new PathBasedDecoratorSelector()
 * &nbsp;    .put("/*", "/decorators/default.html")
 * &nbsp;    .put("/admin/*", "/decorators/admin.html")
 * &nbsp;    .put("/thingy", "/decorators/thingy.html")
 * </pre>
 * @see PathMapper
 *
 * @author Joe Walnes
 */
public class PathBasedDecoratorSelector implements DecoratorSelector {

    private static final String[] EMPTY = {};

    private final PathMapper<String[]> pathMapper = new PathMapper<String[]>();

    public PathBasedDecoratorSelector put(String contentPath, String... decoratorPaths) {
        pathMapper.put(contentPath, decoratorPaths);
        return this;
    }

    @Override
    public String[] selectDecoratorPaths(ContentProperty content, SiteMeshContext siteMeshContext) throws IOException {
        String[] result = pathMapper.get(siteMeshContext.getRequestPath());
        return result == null ? EMPTY : result;
    }
}