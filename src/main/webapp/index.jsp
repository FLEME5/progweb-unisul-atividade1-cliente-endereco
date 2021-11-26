<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Trabalho Final</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  <style>
    body {
        background: #009879;
    }
    .container {
        padding: 15px;
        margin: 15px;
    }
  </style>
  <script type="text/javascript">
    $(document).ready(function() {
        $('#buttonBuscar').click(function ()
        {
            var cidade = $('#cidade').val();
            var $clienteTable = $('#clientes > table > tbody');
            $.ajax({
                type: "GET",
                url: "ClientesCidade?cidade="+cidade,
                dataType: 'json'
            }).done(function(data) {
                $("#tabelaClientes").empty()
                $("#cidadeuf").empty()
                $("#cidadeuf").append($('<p>').html('Cidade: ' + data.cidade))
                $("#cidadeuf").append($('<p>').html('UF: ' + data.uf))
                var responseClientes = data.clientes;

                $.each(responseClientes, function(key, value) {
                  $('<tr>')
                      .append($('<td>').html(value.idCliente))
                      .append($('<td>').html(value.nome))
                      .appendTo($clienteTable);
                });
            });
        });
    });
  </script>
</head>
<body>
<div class="jumbotron text-center bg-dark" style="margin-bottom:0;">
  <h1 class="text-light">Trabalho Final</h1>
</div>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="crudcliente.jsp">Clientes</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="crudendereco.jsp">Endereços</a>
      </li>
    </ul>
  </div>
</nav>
  
<div class="contatiner bg-dark text-light text-center"  style="padding-bottom: 50px">
  <form class="d-flex justify-content-center align-items-center">  
    <div class="container">
      <label class="text-light">Cidade:</label>
      <input type="text" class="form-control" id="cidade">
      <button type="button" class="btn btn-light" id="buttonBuscar" style="margin: 15px">Buscar
    </div>
  </form>
  <div id="cidadeuf" class="text-center">
  </div>
  <div class="d-flex justify-content-center align-items-center" id="clientes">
    <table class="table table-dark table-hover" style="max-width: 50%">
      <thead>
          <tr>
              <th>ID</th>
              <th>Nome</th>
          </tr>
      </thead>
      <tbody id="tabelaClientes">
      </tbody>
    </table>
  </div>
</div>

</body>
</html>
