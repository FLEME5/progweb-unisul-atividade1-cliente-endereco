package br.unisul.aula.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import br.unisul.aula.banco.Banco;
import br.unisul.aula.banco.ClienteImpl;
import br.unisul.aula.banco.EnderecoImpl;
import br.unisul.aula.dto.ClienteDTO;
import br.unisul.aula.modelo.Cliente;
import br.unisul.aula.modelo.Endereco;

@WebServlet(name = "ClienteServlet", urlPatterns={"/Clientes", "/Cliente/*"} )
public class ClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String[] requestUri = request.getRequestURI().split("/");
        String path = requestUri[2];

        if (path.equals("Clientes")) {

            Banco<Cliente> banco = new ClienteImpl();
            List<Cliente> clienteList = banco.findAll();
            List<ClienteDTO> dtos = new ArrayList<>();
            for (int i = 0; i < clienteList.size(); i++) {
                ClienteDTO dto = new ClienteDTO(clienteList.get(i));
                dtos.add(dto);
            }
            Gson gson = new Gson();
            String clienteJson = gson.toJson(dtos);
            response.getWriter().println(clienteJson);
        } else if (path.equals("Cliente")) {

            try {
                //String[] pathInfo = request.getPathInfo().split("/");
                //String param = pathInfo[1];
                String param = request.getParameter("id");
                Banco<Cliente> banco = new ClienteImpl();
                Cliente cliente = banco.findById(Long.parseLong(param));
                ClienteDTO dto = new ClienteDTO(cliente);
                Gson gson = new Gson();
                String clienteJson = gson.toJson(dto);
                response.getWriter().println(clienteJson);
            
            } catch (EntityNotFoundException e) {
                
                response.getWriter().println("Nenhum usuário encontrado com este ID!");
            } catch (NumberFormatException e) {
                
                response.getWriter().println("ID Inválido, digite um número!");
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Falha ao buscar usuário devido exceção");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        BufferedReader buffreader = request.getReader();
        Gson gson = new Gson();
        ClienteDTO dto = gson.fromJson(buffreader, ClienteDTO.class);
        Endereco endereco = dto.getEndereco().converterEndereco();
        Cliente cliente = dto.converterCliente(endereco);
        Banco<Endereco> bancoEndereco = new EnderecoImpl();
        bancoEndereco.insert(endereco);
        Banco<Cliente> banco = new ClienteImpl();
        banco.insert(cliente);
        response.getWriter().println("{\"Mensagem\":\"OK\"}");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        try {
            //String[] pathInfo = request.getPathInfo().split("/");
            //String param = pathInfo[1];
            //cliente?id=2
            String param = request.getParameter("id");
            Banco<Cliente> banco = new ClienteImpl();
            Cliente cliente = banco.findById(Long.parseLong(param));
            BufferedReader buffreader = request.getReader();
            Gson gson = new Gson();
            ClienteDTO dto = gson.fromJson(buffreader, ClienteDTO.class);    
            Banco<Endereco> bancoEndereco = new EnderecoImpl();
            
            Endereco endereco = ((EnderecoImpl)bancoEndereco).findByCep(dto.getEndereco().getCep());
            cliente.setEndereco(endereco);
            cliente.setNome(dto.getNome());
            cliente.setNumero(dto.getNumero());
            cliente.setComplemento(dto.getComplemento());
            banco.update(cliente);
            response.getWriter().println("{\"Mensagem\":\"OK\"}");
        
        } catch (EntityNotFoundException e) {
            
            response.getWriter().println("Nenhum usuário encontrado com este ID!");
        } catch (NumberFormatException e) {
            
            response.getWriter().println("ID Inválido, digite um número!");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Falha ao buscar usuário devido exceção");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        try {
            String param = request.getParameter("id");
            Banco<Cliente> banco = new ClienteImpl();
            
            ((ClienteImpl) banco).deleteById(Long.parseLong(param));
            response.getWriter().println("{\"Mensagem\":\"OK\"}");
        
        } catch (EntityNotFoundException e) {
            
            response.getWriter().println("Nenhum usuário encontrado com este ID!");
        } catch (NumberFormatException e) {
            
            response.getWriter().println("ID Inválido, digite um número!");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Falha ao buscar usuário devido exceção");
        } 
    }

}
