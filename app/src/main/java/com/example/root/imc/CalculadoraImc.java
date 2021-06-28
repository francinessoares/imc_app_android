package com.example.root.imc;

/**
 * Created by root on 26/04/18.
 */

public class CalculadoraImc {

    public final static String MUITO_ABAIXO = "Abaixo II";
    public final static String ABAIXO = "Abaixo I";
    public final static String NORMAL = "Ideal";
    public final static String SOBREPESO = "Sobrepeso";
    public final static String OBESIDADE = "Obesidade";


    public Aluno calcula(Aluno aluno,  int idRadioSelecionado) throws Exception{

        Double resultadoImc = aluno.peso / (aluno.altura * aluno.altura);
        String resultadoCondicaoImc = null;

        if (aluno.idade <= 15) {
            resultadoCondicaoImc = calculaIMCInfantil(idRadioSelecionado, aluno.idade, resultadoImc);
        } else {
            resultadoCondicaoImc = calculaIMCAdulto(resultadoImc);
        }

        aluno.classificacao = resultadoCondicaoImc;
        aluno.imc = resultadoImc;

        return aluno;

    }

    private String calculaIMCInfantil(int idRadioSelecionado, Integer idade, Double resultadoImc) throws Exception {
        String resultado = null;

        if (idade > 5) {
            if (idRadioSelecionado == R.id.mascRadio) {
                resultado = calculaImcMeninas(idade, resultadoImc);
            } else if (idRadioSelecionado == R.id.femRadio) {
                resultado = calculaImcMeninos(idade, resultadoImc);
            } else {
                throw new Exception("Escolha o sexo.");
            }
            return resultado;
        } else {
            throw new Exception("A idade deve ser maior que 5 anos.");
        }
    }

    private String calculaIMCAdulto(Double resultadoImc) {
        String resultado = null;
        if (resultadoImc < 17) {
            resultado = MUITO_ABAIXO;
        } else if (resultadoImc >= 17 && resultadoImc < 18.5) {
            resultado = ABAIXO;
        } else if (resultadoImc >= 18.5 && resultadoImc < 25) {
            resultado = NORMAL;
        } else if (resultadoImc >= 25 && resultadoImc <= 30) {
            resultado = SOBREPESO;
        } else {
            resultado = OBESIDADE;
        }
        return resultado;
    }

    private String calculaImcMeninas(Integer idade, Double resultadoImc) {
        String resultado = null;
        switch (idade) {
            case 6:
                if (resultadoImc < 14.3) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 14.3 && resultadoImc <= 16.1) {
                    resultado = NORMAL;
                } else if (resultadoImc > 16.1 && resultadoImc < 17.4) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 7:
                if (resultadoImc < 14.9) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 14.9 && resultadoImc <= 17.1) {
                    resultado = NORMAL;
                } else if (resultadoImc > 17.1 && resultadoImc < 18.9) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 8:
                if (resultadoImc < 15.6) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 15.6 && resultadoImc <= 18.1) {
                    resultado = NORMAL;
                } else if (resultadoImc > 18.1 && resultadoImc < 20.3) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 9:
                if (resultadoImc < 16.3) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 16.3 && resultadoImc <= 19.1) {
                    resultado = NORMAL;
                } else if (resultadoImc > 19.1 && resultadoImc < 21.7) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 10:
                if (resultadoImc < 17) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 17 && resultadoImc <= 20.1) {
                    resultado = NORMAL;
                } else if (resultadoImc > 20.1 && resultadoImc < 23.2) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 11:
                if (resultadoImc < 17.6) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 17.6 && resultadoImc <= 21.1) {
                    resultado = NORMAL;
                } else if (resultadoImc > 21.1 && resultadoImc < 24.5) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 12:
                if (resultadoImc < 18.3) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 18.3 && resultadoImc <= 22.1) {
                    resultado = NORMAL;
                } else if (resultadoImc > 22.1 && resultadoImc < 25.9) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 13:
                if (resultadoImc < 18.9) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 18.9 && resultadoImc <= 23) {
                    resultado = NORMAL;
                } else if (resultadoImc > 23 && resultadoImc < 27.7) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 14:
                if (resultadoImc < 19.3) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 19.3 && resultadoImc <= 23.8) {
                    resultado = NORMAL;
                } else if (resultadoImc > 23.8 && resultadoImc < 27.9) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 15:
                if (resultadoImc < 19.6) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 19.6 && resultadoImc <= 24.2) {
                    resultado = NORMAL;
                } else if (resultadoImc > 24.2 && resultadoImc < 28.8) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            default:
                return null;

        }
        return resultado;
    }

    private String calculaImcMeninos(Integer idade, Double resultadoImc) {
        String resultado = null;
        switch (idade) {
            case 6:
                if (resultadoImc < 14.5) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 14.5 && resultadoImc <= 16.6) {
                    resultado = NORMAL;
                } else if (resultadoImc > 16.6 && resultadoImc < 18) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 7:
                if (resultadoImc < 15) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 15 && resultadoImc <= 17.3) {
                    resultado = NORMAL;
                } else if (resultadoImc > 17.3 && resultadoImc < 19.1) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 8:
                if (resultadoImc < 15.6) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 15.6 && resultadoImc <= 16.7) {
                    resultado = NORMAL;
                } else if (resultadoImc > 16.7 && resultadoImc < 20.3) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 9:
                if (resultadoImc < 16.1) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 16.1 && resultadoImc <= 18.8) {
                    resultado = NORMAL;
                } else if (resultadoImc > 18.8 && resultadoImc < 21.4) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 10:
                if (resultadoImc < 16.7) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 16.7 && resultadoImc <= 19.6) {
                    resultado = NORMAL;
                } else if (resultadoImc > 19.6 && resultadoImc < 22.5) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 11:
                if (resultadoImc < 17.2) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 17.2 && resultadoImc <= 20.3) {
                    resultado = NORMAL;
                } else if (resultadoImc > 20.3 && resultadoImc < 23.7) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 12:
                if (resultadoImc < 17.8) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 17.8 && resultadoImc <= 21.1) {
                    resultado = NORMAL;
                } else if (resultadoImc > 21.1 && resultadoImc < 24.8) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 13:
                if (resultadoImc < 18.5) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 18.5 && resultadoImc <= 21.9) {
                    resultado = NORMAL;
                } else if (resultadoImc > 21.9 && resultadoImc < 25.9) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 14:
                if (resultadoImc < 19.2) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 19.2 && resultadoImc <= 22.7) {
                    resultado = NORMAL;
                } else if (resultadoImc > 22.7 && resultadoImc < 26.9) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            case 15:
                if (resultadoImc < 19.9) {
                    resultado = ABAIXO;
                } else if (resultadoImc >= 19.9 && resultadoImc <= 23.6) {
                    resultado = NORMAL;
                } else if (resultadoImc > 23.6 && resultadoImc < 27.7) {
                    resultado = SOBREPESO;
                } else {
                    resultado = OBESIDADE;
                }
                break;
            default:
                return null;

        }
        return resultado;
    }

}
