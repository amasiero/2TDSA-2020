package com.andreymasiero.gerenciador.empresas.ws;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.andreymasiero.gerenciador.empresas.ws.EmpresaWSStub.Adiciona;
import com.andreymasiero.gerenciador.empresas.ws.EmpresaWSStub.AdicionaResponse;

public class TesteWS {

	public static void main(String[] args) throws AxisFault {
		try {
			EmpresaWSStub stub = new EmpresaWSStub();
			
			Adiciona adiciona = new Adiciona();
			adiciona.setNome("Facebook");
			AdicionaResponse response = stub.adiciona(adiciona);
			System.out.println(response.get_return().getNome());
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
