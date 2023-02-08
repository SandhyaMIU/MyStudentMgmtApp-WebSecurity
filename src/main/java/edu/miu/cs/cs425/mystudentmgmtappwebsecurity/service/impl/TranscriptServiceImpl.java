package edu.miu.cs.cs425.mystudentmgmtappwebsecurity.service.impl;

import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.model.Transcript;
import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.repository.TranscriptRepository;
import edu.miu.cs.cs425.mystudentmgmtappwebsecurity.service.TranscriptService;
import org.springframework.stereotype.Service;

@Service
public class TranscriptServiceImpl implements TranscriptService {

    private TranscriptRepository transcriptRepository;

    public TranscriptServiceImpl(TranscriptRepository transcriptRepository) {
        this.transcriptRepository = transcriptRepository;
    }

    @Override
    public Transcript addNewTranscript(Transcript newTranscript) {
        return transcriptRepository.save(newTranscript);
    }


}
