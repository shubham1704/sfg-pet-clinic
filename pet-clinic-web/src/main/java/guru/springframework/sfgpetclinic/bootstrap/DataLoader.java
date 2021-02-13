package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

  private final OwnerService ownerService;
  private final VetService vetService;
  private final PetTypeService petTypeService;

  @Autowired
  public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
    this.ownerService = ownerService;
    this.vetService = vetService;
    this.petTypeService = petTypeService;
  }


  @Override
  public void run(String... args) throws Exception {

    PetType dog = new PetType();
    dog.setName("Dog");
    PetType savedDogPetType = petTypeService.save(dog);

    PetType cat = new PetType();
    dog.setName("Cat");
    PetType savedCatPetType = petTypeService.save(cat);
    System.out.println("Loaded Pets");

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

    Vet vet1 = new Vet();
    vet1.setFirstName("Sam");
    vet1.setLastName("Axe");

    vetService.save(vet1);

    Vet vet2 = new Vet();
    vet2.setFirstName("Shubham");
    vet2.setLastName("Jaiswal");

    vetService.save(vet2);

    System.out.println("Loaded Vets.....");

  }
}
