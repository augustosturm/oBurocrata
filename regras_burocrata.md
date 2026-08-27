# Regras do Burocrata — Trabalho Prático 1

## 🗂️ Restrições estruturais da mesa

| Restrição | Valor |
|---|---|
| Processos abertos simultaneamente na mesa | **5** |
| Páginas máximas por processo | **250** (acima disso → rompimento da pasta + perda dos documentos + advertência) |
| Cursos atendidos simultaneamente | **10** |
| Reposição de processo aberto | Um novo processo vazio é criado **assim que possível** após um despacho (não imediatamente) |

---

## 📋 Regras de organização/despacho de processos

| # | Regra | Tipo de restrição | Exemplo / Observação |
|---|-------|-------------------|------------------------|
| **1** | Um processo não pode conter documentos de **graduação** e **pós-graduação** ao mesmo tempo no despacho | Nível do curso | — |
| **2** | Não pode misturar **Documentos Administrativos** e **Documentos Acadêmicos** no mesmo processo | Categoria do documento | **Atas** são exceção — podem estar em qualquer processo. Ex.: Norma (Administrativo) não pode ir com Histórico (Acadêmico) |
| **3** | Um processo não pode ser despachado **contendo apenas Atas** | Composição mínima | Precisa de pelo menos um documento que não seja Ata |
| **4** | Portaria ou Edital com **≥ 100 páginas** = "documento substancial" → deve ser despachado **sozinho** no processo | Regra especial por tamanho | **Exceção:** se a Portaria/Edital **não é mais válida**, pode ir junto de outros documentos mesmo sendo substancial |
| **5** | Circulares e Ofícios diferentes só podem estar juntos se tiverem **um destinatário em comum** | Compatibilidade por destinatário | Ofício→Ana Moura + Circular→Dulce Pontes,Ana Moura + Circular→Ana Moura,António,Amália = **válido** (Ana Moura é comum a todos) |
| **6** | Diplomas só podem ser despachados junto de **outros Diplomas, Certificados ou Atas** | Restrição de acompanhamento | Diploma não pode ir com Histórico, Atestado, etc. |
| **7** | Atestados de **categorias diferentes** não podem estar no mesmo processo | Subtipo do documento | Atestados da mesma categoria podem coexistir |

---

## ⚖️ Prioridades de avaliação (o que pesa mais)

1. **Evitar aumento de estresse** (descumprir regras ou perder documentos) — pior que baixa eficiência
2. **Eficiência de despacho** — poucos processos, bem preenchidos (evitar desperdício de espaço)

### Fórmula de eficiência (usada na competição, não na nota)

```
Eficiência = (documentos despachados / processos despachados)
           × (documentos despachados / documentos criados)
           ÷ (estresse do burocrata + 1)
```

---

## 🌳 Hierarquia de classes (Documento)

```
Documento (abstrata)
├── Ata (concreta)
├── DocumentoAdministrativo (abstrata)
│   ├── Norma (concreta)
│   └── Deliberacao (abstrata)
│       ├── Portaria (concreta)
│       ├── Edital (concreta)
│       ├── Circular (concreta)
│       └── Oficio (concreta)
└── DocumentoAcademico (abstrata)
    ├── Plano (concreta)
    └── Registro (abstrata)
        ├── Certificado (concreta)
        │   └── Diploma (concreta)
        ├── Historico (concreta)
        └── Atestado (concreta)
```

---

## ✅ Checklist rápido antes de despachar um processo

- [ ] Todos os documentos são do mesmo nível (graduação **ou** pós)?
- [ ] Não há mistura de Administrativo + Acadêmico (exceto Atas)?
- [ ] Não é um processo só de Atas?
- [ ] Se há Portaria/Edital ≥100 páginas válido → está sozinho no processo?
- [ ] Circulares/Ofícios diferentes têm destinatário em comum?
- [ ] Diplomas só estão com Diploma/Certificado/Ata?
- [ ] Atestados são todos da mesma categoria?
- [ ] Total de páginas ≤ 250?
