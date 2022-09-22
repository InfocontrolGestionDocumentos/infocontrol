package com.app.infocontrol.commons;



import com.app.infocontrol.data.pojo.response.ResponseEmpleados.DataEmpleados;

import java.util.List;

public class EmpleadosPorEmpresa {

    private String idEmpresa;
    private List<DataEmpleados> empleados;

    public EmpleadosPorEmpresa() {
    }

    public EmpleadosPorEmpresa(String idEmpresa, List<DataEmpleados> empleados) {
        this.idEmpresa = idEmpresa;
        this.empleados = empleados;
    }

    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public List<DataEmpleados> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<DataEmpleados> empleados) {
        this.empleados = empleados;
    }
}
