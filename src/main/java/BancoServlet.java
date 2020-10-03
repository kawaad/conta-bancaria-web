import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BancoServlet") // dshadisja
public class BancoServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String paramNomeCliente = request.getParameter("nomeCliente");
		String paramTipoCliente = request.getParameter("tipoCliente");
		String paramCpfCnpj = request.getParameter("cpfCnpj");
		String paramOperacao = request.getParameter("operacao");
		String paramDeposito = request.getParameter("valorDeposito");
		String paramSaque = request.getParameter("valorSaque");

		Cliente cliente;
		if (paramTipoCliente.equals("pessoaFisica")) {
			cliente = new PessoaFisica(paramNomeCliente, paramCpfCnpj);
		} else {
			cliente = new PessoaJuridica(paramNomeCliente, paramCpfCnpj);
		}
		ContaBancaria contabancaria = new ContaBancaria(cliente);
		
		String resposta = "";
		int resposta1 = 0; 
		switch (paramOperacao){
		case "recuperarNomeCliente":
			resposta = cliente.getNome();
			break;
		case "recuperarCpfCliente":
			if  (paramTipoCliente.equals("pessoaFisica")) {
				resposta = ((PessoaFisica)cliente).getCpf();
			} else {
				resposta = "Não é uma pessoa física";
			}
			break;
		case "recuperarCnpjCliente":
			if  (paramTipoCliente.equals("pessoaJuridica")) {
				resposta = ((PessoaJuridica)cliente).getCnpj();
			} else {
				resposta = "Não é uma pessoa juridica";
			}
			break;
		case "recuperarSaldo": 
			resposta1 = contabancaria.getSaldo();
			request.setAttribute("resposta", resposta1);
			RequestDispatcher reqDispatcher1 = request.getRequestDispatcher("resposta.jsp");
			reqDispatcher1.forward(request, response);
		case "depositarValor":
			int intParamDeposito = Integer.parseInt(paramDeposito);
			resposta1 = contabancaria.depositar(intParamDeposito);
			request.setAttribute("resposta", resposta1);
			RequestDispatcher reqDispatcher2 = request.getRequestDispatcher("resposta.jsp");
			reqDispatcher2.forward(request, response);
		case "sacarValor":
			int intParamSaque = Integer.parseInt(paramSaque);
			resposta1 = contabancaria.sacar(intParamSaque);
			request.setAttribute("resposta", resposta1);
			RequestDispatcher reqDispatcher3 = request.getRequestDispatcher("resposta.jsp");
			reqDispatcher3.forward(request, response);
		case "depositarSacarValor":
			int intParamDeposito1 = Integer.parseInt(paramDeposito);
			int intParamSaque1 = Integer.parseInt(paramSaque);
			int depositando = contabancaria.depositar(intParamDeposito1);
			int sacando = contabancaria.sacar(intParamSaque1);
			request.setAttribute("resposta", depositando);
			request.setAttribute("resposta", sacando);
			RequestDispatcher reqDispatcher4 = request.getRequestDispatcher("resposta.jsp");
			reqDispatcher4.forward(request, response);
		}
 	
		request.setAttribute("resposta", resposta);
		RequestDispatcher reqDispatcher = request.getRequestDispatcher("resposta.jsp");
		reqDispatcher.forward(request, response);
	}
}
