/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ti.umy.tugas.classB.sayuran;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author user
 */
@Controller
public class projectController {
    @RequestMapping("/sayuran")
    public String getInput(HttpServletRequest sayur, Model model){
       String inputNama = sayur.getParameter("namasayur");
       String inputHarga = sayur.getParameter("hargaperkilo");
       String inputJmlbeli = sayur.getParameter("jumlahbeli");
       String uangBayar = sayur.getParameter("uangbayar");
       
       
       Double hargaSayur = Double.valueOf(inputHarga);      
       Double JumlahBeli = Double.valueOf(inputJmlbeli);
       Double jumlahBayar = hargaSayur * JumlahBeli;      
       Double totalBayar = null;
       Double uang = Double.valueOf(uangBayar);
              
       String diskon = "";
       
       if (jumlahBayar < 16000)
       {
           totalBayar = jumlahBayar - (0*jumlahBayar/100);
           diskon = "0%";
       }
       else if (jumlahBayar >= 16000 || jumlahBayar < 25000)
       {
           totalBayar = jumlahBayar - (10*jumlahBayar/100);
           diskon = "10%";
       }
       else if (jumlahBayar >= 25000)
       {
           totalBayar = jumlahBayar - (15*jumlahBayar/100);
           diskon = "15%";
       }
       
                   
      String pesan = null;
      Double sisa = uang - totalBayar;
      if (uang <totalBayar)
      {
          pesan = "Uang anda kurang " + sisa;
      }
      
      else if (Objects.equals(uang, totalBayar))
      { 
          pesan = "Uang anda Pas";
      }
      
      else if (uang> totalBayar)
      {
          pesan = "Uang kembalian anda adalah "+ (uang-totalBayar);
      }
      
      
       
       model.addAttribute("nama", inputNama);
       model.addAttribute("harga", hargaSayur);
       model.addAttribute("Jmlbeli", inputJmlbeli);
       model.addAttribute("Jmlbayar", jumlahBayar);       
       model.addAttribute("disk", diskon);
       model.addAttribute("total", totalBayar);
       model.addAttribute("pesan", pesan);
       model.addAttribute("uang", uangBayar);
       model.addAttribute("kembali", sisa);
       return "view";

    }
}
