# -*- coding: utf-8 -*-

import sys
from PyQt4 import QtGui, uic


MainWindowUI, MainWindowBase = uic.loadUiType("cliente.ui")


class Cliente(MainWindowUI, MainWindowBase):
    def __init__(self, parent=None):
        super().__init__()
        self.setupUi(self)
        self.redimensionar()

    def redimensionar(self):
    	self.table.horizontalHeader().setStretchLastSection(True)
    	self.table.verticalHeader().setStretchLastSection(True)
    	self.table.horizontalHeader().setResizeMode(QtGui.QHeaderView.Stretch)
    	self.table.verticalHeader().setResizeMode(QtGui.QHeaderView.Stretch)


app = QtGui.QApplication(sys.argv)
window = Cliente()
window.show()
sys.exit(app.exec_())
