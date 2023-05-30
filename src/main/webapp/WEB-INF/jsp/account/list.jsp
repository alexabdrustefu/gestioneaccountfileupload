<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html lang="it" class="h-100">
<head>

<!-- Common imports in pages -->
<jsp:include page="../header.jsp" />

<title>Pagina dei Risultati</title>
</head>

<body class="d-flex flex-column h-100">

	<!-- Fixed navbar -->
	<jsp:include page="../navbar.jsp"></jsp:include>

	<!-- Begin page content -->
	<main class="flex-shrink-0">
		<div class="container">

			<div
				class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }"
				role="alert">
				${successMessage}
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<div class="alert alert-danger alert-dismissible fade show d-none"
				role="alert">
				Esempio di operazione fallita!
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>
			<div class="alert alert-info alert-dismissible fade show d-none"
				role="alert">
				Aggiungere d-none nelle class per non far apparire
				<button type="button" class="btn-close" data-bs-dismiss="alert"
					aria-label="Close"></button>
			</div>



			<div class='card'>
				<div class='card-header'>
					<h5>Lista dei risultati</h5>
				</div>
				<div class='card-body'>
					<a class="btn btn-warning"
						href="${pageContext.request.contextPath}/account/insert">Inserisci
						nuovo</a>

					<div class='table-responsive'>
						<table class='table table-striped '>
							<thead>
								<tr>
									<th>Id</th>
									<th>Nome</th>
									<th>Cognome</th>
									<th>Email</th>
									<th>Data Creazione</th>
									<th>Azioni</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${account_list_attribute }" var="accountItem">
									<tr>
										<td>${accountItem.id }</td>
										<td>${accountItem.nome}</td>
										<td>${accountItem.cognome}</td>
										<td>${accountItem.email }</td>
										<td><fmt:parseDate value="${accountItem.dataCreazione}"
												pattern="yyyy-MM-dd" var="localDateToBeParsed" type="date" />
											<fmt:formatDate pattern="dd/MM/yyyy"
												value="${localDateToBeParsed}" /></td>
										<td><a class="btn  btn-sm btn-outline-secondary"
											href="${pageContext.request.contextPath}/account/show/${accountItem.id }">Visualizza</a>
											<a class="btn  btn-sm btn-outline-primary"
											href="${pageContext.request.contextPath}/account/editAccount/${accountItem.id }">Modifica</a>
											<a class="btn  btn-sm btn-outline-danger"
											href="${pageContext.request.contextPath}/account/deleteAccount/${accountItem.id }">Elimina</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>

					<!-- end card-body -->
				</div>
				<!-- end card -->
			</div>


			<!-- end container -->
		</div>

	</main>
	<!-- Footer -->
	<jsp:include page="../footer.jsp" />
</body>
</html>