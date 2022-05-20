package br.com.senai.manutencaosenaiapi.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.senai.manutencaosenaiapi.entity.TipoDePeca;

public class TipoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private final int QTDE_COLUNAS = 3;
	private List<TipoDePeca> tipos;

	public TipoTableModel(List<TipoDePeca> tipos) {
		this.tipos = tipos;
	}

	@Override
	public int getRowCount() {
		return tipos.size();
	}

	@Override
	public int getColumnCount() {
		return QTDE_COLUNAS;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "ID";
		} else if (column == 1) {
			return "Descrição";
		}
		throw new IllegalArgumentException("Índice inválido");
	}
	
	public TipoDePeca getPor(int rowIndex) {
		return tipos.get(rowIndex);
	}
	
	public void removerPor(int rowIndex) {
		tipos.remove(rowIndex);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {

		if (columnIndex == 0) {
			return tipos.get(rowIndex).getId();
		} else if (columnIndex == 1) {
			return tipos.get(rowIndex).getDescricao();
		}
		
		throw new IllegalArgumentException("Índice inválido");
	}

}
