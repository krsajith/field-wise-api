package com.unnathy.fieldwise.message;

import com.unnathy.fieldwise.core.BasicEntityService;
import com.unnathy.fieldwise.core.ModelMapperService;
import com.unnathy.fieldwise.core.UnnathyError;
import com.unnathy.fieldwise.message.MessageDTO;
import com.unnathy.fieldwise.message.Message;
import com.unnathy.fieldwise.user.User;
import com.unnathy.fieldwise.message.MessageRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MessageService implements BasicEntityService<MessageDTO, Long> {

    private final MessageRepository repository;
    private final ModelMapperService modelMapperService;

    @Override
    public MessageDTO post(MessageDTO data, String authorization, User principal) throws UnnathyError {
        Message entity = modelMapperService.map(data, Message.class);
        Message saved = repository.save(entity);
        return modelMapperService.map(saved, MessageDTO.class);
    }

    @Override
    public MessageDTO patch(MessageDTO data, String authorization, User principal) throws UnnathyError {
        return put(data, authorization, principal);
    }

    @Override
    public MessageDTO put(MessageDTO data, String authorization, User principal) throws UnnathyError {
        Message entity = modelMapperService.map(data, Message.class);
        Message saved = repository.save(entity);
        return modelMapperService.map(saved, MessageDTO.class);
    }

    @Override
    public List<MessageDTO> get(String authorization, User principal) throws UnnathyError {
        return repository.findAll().stream()
                .map(entity -> modelMapperService.map(entity, MessageDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MessageDTO getWithId(String authorization, User principal, Long id) throws UnnathyError {
        return repository.findById(id)
                .map(entity -> modelMapperService.map(entity, MessageDTO.class))
                .orElseThrow(() -> new UnnathyError("NOT_FOUND", "Message not found", null));
    }
}
