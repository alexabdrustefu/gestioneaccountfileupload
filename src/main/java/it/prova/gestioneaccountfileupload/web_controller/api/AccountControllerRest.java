package it.prova.gestioneaccountfileupload.web_controller.api;
import java.util.List;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestioneaccountfileupload.dto.AccountDTO;
import it.prova.gestioneaccountfileupload.model.Account;
import it.prova.gestioneaccountfileupload.service.AccountService;


@RestController
@RequestMapping("/api/account")
public class AccountControllerRest {
	@Autowired
	private AccountService accountService;
	@GetMapping
	public List<AccountDTO> getAll() {
		return AccountDTO.createAcoountDTOFromModelList(accountService.listAllElements());
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public AccountDTO createNew(@RequestBody AccountDTO accountDTO) {
		Account accountTransient = accountDTO.buildModel();
		accountService.inserisciNuovo(accountTransient);
		// Verifica il tipo di file della proprietà "foto"
		byte[] fotoBytes = accountTransient.getFoto();
		String mimeType = new Tika().detect(fotoBytes);
		if (!mimeType.equals("image/jpeg") && !mimeType.equals("image/jpg") && !mimeType.equals("image/gif")) {
		throw new IllegalArgumentException("La proprietà 'foto' deve essere un'immagine JPEG, JPG o GIF");
		}
		return AccountDTO.buildAccountDTOFromModel(accountTransient);
	}
	@PutMapping("/{id}")
	public AccountDTO aggiorna(@RequestBody AccountDTO accountDTO,@PathVariable(required = true)  Long id) {
		Account attachmentTransient = accountDTO.buildModel();
		attachmentTransient.setId(id);
		accountService.aggiorna(attachmentTransient);
		return AccountDTO.buildAccountDTOFromModel(attachmentTransient);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void elimina(@PathVariable(required = true)Long id) {
		accountService.rimuoviById(id);
	}
	

}
