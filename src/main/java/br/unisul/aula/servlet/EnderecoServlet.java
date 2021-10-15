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
import br.unisul.aula.banco.EnderecoImpl;
import br.unisul.aula.dto.EnderecoDTO;
import br.unisul.aula.modelo.Endereco;
import br.unisul.aula.modelo.UnidadeFederativa;

@WebServlet(name = "EnderecoServlet", urlPatterns={"/Enderecos", "/Endereco/*"} )
public class EnderecoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String[] requestUri = request.getRequestURI().split("/");
        String path = requestUri[2];

        if (path.equals("Enderecos")) {

            Banco<Endereco> banco = new EnderecoImpl();
            List<Endereco> enderecoList = banco.findAll();
            List<EnderecoDTO> dtos = new ArrayList<>();            
            for (int i = 0; i < enderecoList.size(); i++) {
                EnderecoDTO dto = new EnderecoDTO(enderecoList.get(i));
                dtos.add(dto);
            }
            Gson gson = new Gson();
            String enderecoJson = gson.toJson(dtos);
            response.getWriter().println(enderecoJson);
        } else if (path.equals("Endereco")) {

            try {
                String param = request.getParameter("id");
                System.out.println(param);
                Banco<Endereco> banco = new EnderecoImpl();
                Endereco endereco = ((EnderecoImpl)banco).findById(Long.parseLong(param));
                EnderecoDTO dto = new EnderecoDTO(endereco);
                Gson gson = new Gson();
                String enderecoJson = gson.toJson(dto);
                response.getWriter().println(enderecoJson);
            
            } catch (EntityNotFoundException e) {
                
                response.getWriter().println("Nenhum endereço encontrado com este ID!");
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
        EnderecoDTO dto = gson.fromJson(buffreader, EnderecoDTO.class);
        Endereco endereco = dto.converterEndereco();
        Banco<Endereco> bancoEndereco = new EnderecoImpl();
        bancoEndereco.insert(endereco);
        response.getWriter().println("{\"Mensagem\":\"OK\"}");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");

        try {
            String param = request.getParameter("id");
            Banco<Endereco> banco = new EnderecoImpl();
            Endereco endereco = banco.findById(Long.parseLong(param));
            BufferedReader buffreader = request.getReader();
            Gson gson = new Gson();
            EnderecoDTO dto = gson.fromJson(buffreader, EnderecoDTO.class);
            
            endereco.setLogradouro(dto.getLogradouro());
            endereco.setBairro(dto.getBairro());
            endereco.setCidade(dto.getCidade());
            endereco.setUf(UnidadeFederativa.valueOf(dto.getUf()));
            endereco.setCep(dto.getCep());

            banco.update(endereco);
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