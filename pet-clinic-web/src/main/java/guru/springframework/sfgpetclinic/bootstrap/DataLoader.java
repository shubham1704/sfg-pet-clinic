package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;
  private final SpecialtyService specialtyService;
  private final VisitService visitService;

  @Autowired
  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
    this.specialtyService = specialtyService;
    this.visitService = visitService;
  }


  @Override
  public void run(String... args) throws Exception {

    int count = petTypeService.findAll().size();
    if (count==0){
      loadData();
    }

  }

  private void loadData() {
    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    dog.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);
    System.out.println("Loaded Pets");

    Speciality radiology = new Speciality();
    radiology.setDescription("Radiology");
    Speciality savedRadiology = specialtyService.save(radiology);

    Speciality surgery = new Speciality();
    surgery.setDescription("Surgery");
    Speciality savedSurgery = specialtyService.save(surgery);

    Speciality dentistry = new Speciality();
    dentistry.setDescription("Dentistry");
    Speciality savedDentistry = specialtyService.save(dentistry);

    Owner owner1 = new Owner();
    owner1.setFirstName("Shubham");
    owner1.setLastName("Jaiswal");
    owner1.setAddress("Uttar Pradesh");
    owner1.setCity("Varanasi");
    owner1.setTelephone("9984567696");

    Pet shubhamPet = new Pet();
    shubhamPet.setPetType(savedDogPetType);
    shubhamPet.setOwner(owner1);
    shubhamPet.setBirthDate(LocalDate.now());
    shubhamPet.setName("Rocky");
    owner1.getPets().add(shubhamPet);

    ownerService.save(owner1);

    Owner owner2 = new Owner();
    owner2.setFirstName("Nitika");
    owner2.setLastName("Bharti");
    owner2.setAddress("Bihar");
    owner2.setCity("Patna");
    owner2.setTelephone("2983749239");

    Pet nitikaPet = new Pet();
    nitikaPet.setPetType(savedCatPetType);
    nitikaPet.setOwner(owner2);
    nitikaPet.setBirthDate(LocalDate.now());
    nitikaPet.setName("Catty");
    owner2.getPets().add(nitikaPet);

    ownerService.save(owner2);
    System.out.println("Loaded Owners.....");

    Visit nitikaPetVisit = new Visit();
    nitikaPetVisit.setPet(nitikaPet);
    nitikaPetVisit.setDate(LocalDate.now());
    nitikaPetVisit.setDescription("Thats Very Cute Cat");

    visitService.save(nitikaPetVisit);

    Vet vet1 = new Vet();
    vet1.setFirstName("Mohd");
    vet1.setLastName("Amir");
    vet1.getSpecialties().add(savedRadiology);

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Rohit");
    vet2.setLastName("Gupta");
    vet2.getSpecialties().add(savedSurgery);

    vetService.save(vet2);

    System.out.println("Loaded Vets.....");
  }
}
