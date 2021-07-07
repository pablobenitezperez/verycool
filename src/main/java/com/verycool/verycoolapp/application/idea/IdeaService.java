package com.verycool.verycoolapp.application.idea;

import com.verycool.verycoolapp.domain.idea.IdeaRepository;
import com.verycool.verycoolapp.domain.idea.Idea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class IdeaService {

    private final IdeaRepository ideaRepository;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository) {
        this.ideaRepository = ideaRepository;
    }

    public List<Idea> getAll() {
        return StreamSupport.stream(ideaRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparing(Idea::getId))
                .collect(Collectors.toUnmodifiableList());
    }

    public List<Idea> getAllAfter(UUID id){
        return getAll().stream()
                .dropWhile(idea -> idea.getId().compareTo(id) != 1)
                .collect(Collectors.toUnmodifiableList());
    }

    public Idea create(CreateIdeaInput ideaInput){
        //For entity, we don't use setters and use constructor instead in order to avoid Anemic Model
        Idea idea = new Idea(ideaInput.getTitle(), ideaInput.getText());

        return ideaRepository.save(idea);
    }

    public Idea addIdeaItem(AddIdeaItemInput ideaItemInput){

        Idea idea = ideaRepository.findById(ideaItemInput.getIdeaId())
                .orElseThrow(()-> new EntityNotFoundException("Idea Id associated not found"));

        idea.addIdeaItem(ideaItemInput.getUrl(), ideaItemInput.getIdeaItemType());

        ideaRepository.save(idea);

        return idea;
    }
}
