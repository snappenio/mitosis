package com.snappenio.mitosis.snap.controller;

import com.snappenio.mitosis.codepen.model.Codepen;
import com.snappenio.mitosis.codepen.service.CodepenService;
import com.snappenio.mitosis.snap.model.Snap;
import com.snappenio.mitosis.snap.model.SnapRequest;
import com.snappenio.mitosis.snap.model.SnapResponse;
import com.snappenio.mitosis.snap.repository.SnapRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/snap")
public class SnapController {

    private static final Logger logger = LoggerFactory.getLogger(SnapController.class);

    private final CodepenService codepenService;
    private final SnapRepository snapRepository;

    @Autowired
    public SnapController(CodepenService codepenService, SnapRepository snapRepository) {
        this.codepenService = codepenService;
        this.snapRepository = snapRepository;
    }

    @RequestMapping(value = "/{snapId}", method = RequestMethod.GET)
    public ResponseEntity<Snap> getSnap(@PathVariable String snapId) {
        Optional<Snap> snapOptional = snapRepository.getSnap(snapId);
        if (snapOptional.isPresent()) {
            return new ResponseEntity<>(snapOptional.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<SnapResponse> createSnap(@RequestBody SnapRequest snapRequest) {
        String codepenUrl = snapRequest.getCodepenUrl();

        Optional<Codepen> codepenOptional = codepenService.retrieveCodepen(codepenUrl);
        if (codepenOptional.isPresent()) {
            Codepen codepen = codepenOptional.get();

            Snap snap = new Snap(codepen.getHtml(), codepen.getCss(), codepen.getJs());

            snapRepository.insertSnap(snap);

            String snapId = snap.getSnapId();

            return new ResponseEntity<>(new SnapResponse(snapId), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}

