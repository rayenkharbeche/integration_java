<?php

namespace App\Controller;
use App\Entity\Consultation;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Normalizer\DateTimeNormalizer;
use Symfony\Component\HttpFoundation\JsonResponse;
use App\Repository\ConsultationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;



class ConsultationMController extends AbstractController
{


    /**
     * @Route("/Addcons/{categorie}/{users}/{titre}/{date_creation}/{description}/", name="Addcons")
     */
    public function AddCons (Request $request,$categorie ,$users,$titre,$date_creation,$description) :Response
    {
       // $consultation = new consultation();

      //  Date date1 = new \DateTime();


        $entityManager =$this ->getDoctrine()->getManager()->getConnection();
        $RAW_QUERY="insert into consultation (categorie_id,users_id,titre,date_creation,description,nbr_vus) values ($categorie,$users,$titre,$date_creation,$description,0) ";
        $statement=  $entityManager-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new \Symfony\Component\Serializer\Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("added");

    }

    /**
     * @Route("/updatecons/{id}/{description}/{categorie_id}/{users_id}/{titre}", name="updateCommentaireM")
     */
    public function updateCons(Request $request,$description ,$categorie_id,$users_id,$titre,$id) :Response
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="UPDATE consultation SET description='$description' ,categorie_id='$categorie_id',users_id='$users_id', titre='$titre' WHERE id='$id' ";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("updated");

    }

    /**
     * @Route("/GetCons")
     */
    public function index(Request $request): Response
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="SELECT * FROM consultation ";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $pubs=$statement->fetchAll();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        $formatted = $serializer->normalize(['consultations' => $pubs]);
        return new JsonResponse($formatted);

    }

    /**
     * @Route("/RemoveconsMobile/{id}/", name="deleteconsMobile")
     */

    public function remove($id)
    {
        $conn = $this->getDoctrine()->getManager()
            ->getConnection();
        $RAW_QUERY="Delete FROM consultation WHERE id='$id'";
        $statement=  $conn-> prepare ($RAW_QUERY);
        $statement->execute();
        $serializer = new Serializer([new DateTimeNormalizer(), new ObjectNormalizer()]);
        return new JsonResponse("deleted");

    }



}
