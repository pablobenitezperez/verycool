package com.verycool.verycoolapp.api.graphql.connection;

import com.verycool.verycoolapp.domain.idea.Idea;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.Edge;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Component
public class CursorUtil {

    public ConnectionCursor createCursorWith(UUID id){
        return new DefaultConnectionCursor(
                Base64.getEncoder().encodeToString(id.toString().getBytes(StandardCharsets.UTF_8)));
    }

    public UUID decode(String cursor){
        return UUID.fromString(new String(Base64.getDecoder().decode(cursor)));
    }

    public <T> ConnectionCursor getFirstCursorFrom(List<Edge<T>> edges){
        return edges.isEmpty() ? null : edges.get(0).getCursor();
    }

    public <T> ConnectionCursor getLastCursorFrom(List<Edge<T>> edges){
        return edges.isEmpty() ? null : edges.get(edges.size() - 1).getCursor();
    }

    public boolean hasPreviousPage(String cursor) {
        return cursor != null;
    }

    public boolean hasNextPage(int first, List<Edge<Idea>> edges) {
        return edges.size() >= first;
    }
}
