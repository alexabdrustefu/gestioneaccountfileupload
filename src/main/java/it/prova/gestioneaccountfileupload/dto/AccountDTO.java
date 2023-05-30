package it.prova.gestioneaccountfileupload.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;

import it.prova.gestioneaccountfileupload.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
	private Long id;

	private String nome;

	private String cognome;

	private String email;

	private LocalDate dataCreazione;

	private byte[] foto;

	public Account buildModel() {
		Account result = new Account(this.id, this.nome, this.cognome, this.email, this.dataCreazione, this.foto);
		return result;
	}

	public static AccountDTO buildAccountDTOFromModel(Account accountModel) {
		AccountDTO result = AccountDTO.builder().id(accountModel.getId()).nome(accountModel.getNome())
				.cognome(accountModel.getCognome()).email(accountModel.getEmail())
				.dataCreazione(accountModel.getDataCreazione()).foto(accountModel.getFoto()).build();
		return result;
	}

	public static List<AccountDTO> createAcoountDTOFromModelList(List<Account> modelList) {
		return modelList.stream().map(entity -> AccountDTO.buildAccountDTOFromModel(entity))
				.collect(Collectors.toList());
	}
}
