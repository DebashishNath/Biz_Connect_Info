package biz_connect_info.controllers;

import biz_connect_info.models.ClientDocument;
import biz_connect_info.service.ClientDocument.ClientDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static utils.Constants.BIZ_CONNECT_API_PATH;

@RestController
@RequestMapping(BIZ_CONNECT_API_PATH + "client-document")
@CrossOrigin(origins = "*")
public class ClientDocumentController {

    @Autowired
    private ClientDocumentService
            clientDocumentService;

    @PostMapping("/update")
    public ClientDocument updateClientDocument(
            @RequestBody ClientDocument clientDocument
    ) {
        return clientDocumentService
                .updateClientDocument(clientDocument);
    }

    @DeleteMapping("/delete/{documentId}")
    public void deleteClientDocument(
            @PathVariable Long documentId
    ) {
        clientDocumentService
                .deleteClientDocument(documentId);
    }

    @GetMapping("/{documentId}")
    public ClientDocument getClientDocumentById(
            @PathVariable Long documentId
    ) {
        return clientDocumentService
                .getClientDocumentById(documentId);
    }

    @GetMapping("/all")
    public List<ClientDocument>
    getAllClientDocuments() {
        return clientDocumentService
                .getAllDocuments();
    }
}